package com.gabrielrf.proyectofinal.ui.main

import androidx.lifecycle.*
import com.gabrielrf.proyectofinal.model.DataTeam
import com.gabrielrf.proyectofinal.model.Team
import com.gabrielrf.proyectofinal.model.server.RemoteConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(api_key: String): ViewModel(){
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            val result = RemoteConnection.service.getTeams()

            val teams = result.team.map {
                DataTeam(
                    it.abbreviation,
                    it.city,
                    it.conference,
                    it.division,
                    it.full_name,
                    it.id,
                    it.name
                )
            }
            _state.value = _state.value?.copy(loading = false, teams = teams)
        }
    }

    fun navigateTo(team: DataTeam){
        _state.value = _state.value?.copy(navigateTo = team)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val teams: List<DataTeam>? = null,
        val navigateTo: DataTeam? = null
    )
}

class TeamViewModelFactory(private val api_key: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return TeamViewModel(api_key) as T
    }
}
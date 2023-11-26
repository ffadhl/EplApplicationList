package com.fadhlalhafizh.submissionjetpackcompose.data

import com.fadhlalhafizh.submissionjetpackcompose.model.PremierLeagueClubs
import com.fadhlalhafizh.submissionjetpackcompose.model.PremierLeagueClubsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class ClubsRepository {
    private val dummyPremierLeagueClubs = mutableListOf<PremierLeagueClubs>()

    init {
        if (dummyPremierLeagueClubs.isEmpty()) {
            PremierLeagueClubsData.Epl.forEach {
                dummyPremierLeagueClubs.add(it)
            }
        }
    }

    fun searchPremiereLeagueClubs(query: String) = flow {
        val data = dummyPremierLeagueClubs.filter {
            it.name.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    fun getClubsById(premierLeagueClubsId: Int): PremierLeagueClubs {
        return dummyPremierLeagueClubs.first {
            it.id == premierLeagueClubsId
        }
    }

    fun updatePremiereLeagueClubs(premierLeagueClubsId: Int, newState: Boolean): Flow<Boolean> {
        val i = dummyPremierLeagueClubs.indexOfFirst { it.id == premierLeagueClubsId }
        val result = if (i >= 0) {
            val clubs = dummyPremierLeagueClubs[i]
            dummyPremierLeagueClubs[i] = clubs.copy(isSave = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getSavedClubs(): Flow<List<PremierLeagueClubs>> {
        return flowOf(dummyPremierLeagueClubs.filter { it.isSave })
    }

    companion object {
        @Volatile
        private var instance: ClubsRepository? = null

        fun getInstance(): ClubsRepository = instance ?: synchronized(this) {
            ClubsRepository().apply {
                instance = this
            }
        }
    }
}
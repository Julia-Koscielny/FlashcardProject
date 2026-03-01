package com.example.flashcardproject.Presentation.User

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardproject.Data.User.OfflineUserRepository
import com.example.flashcardproject.Data.User.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import com.example.flashcardproject.Mappers.User.toUI


class UserViewModel( private val userRepository: UserRepository): ViewModel() {
    private val _user_uiState = MutableStateFlow(UserStateUi(emptyList()))
    var userUiState : StateFlow<UserStateUi> = _user_uiState

    init {
        viewModelScope.launch {
            userRepository.users.collect { users ->
                _user_uiState.value = UserStateUi(users)
            }
        }
    }

    fun ensureUserExists(userId: Long){
        viewModelScope.launch {
                if (!userRepository.userExists(userId)){
                    userRepository.insertUser(UserUi(userId,"0"))
                }
            }
        }


    fun getUserPoints(userId: Long): Flow<String> {
        return userRepository
            .getUserPoints(userId)
            .map { points -> "$points"
                Log.d("USER_DEBUG", "User $userId has points: $points")
                "$points"
            }

    }

}
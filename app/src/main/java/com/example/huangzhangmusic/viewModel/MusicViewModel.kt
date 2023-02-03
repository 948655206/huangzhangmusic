package com.example.huangzhangmusic.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.huangzhangmusic.domain.IntentState

class MusicViewModel : ViewModel() {

    val intentState = MutableLiveData<IntentState>()



}

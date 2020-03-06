package com.csci448.a2.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.csci448.a2.R
import com.csci448.a2.data.HistoryDatabase
import com.csci448.a2.ui.History.HistoryViewModel
import com.csci448.a2.ui.History.HistoryViewModelFactory

class PrefScreenFragment: PreferenceFragmentCompat() {
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var  historyDataBase : HistoryDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = HistoryViewModelFactory(requireContext())
        historyViewModel = ViewModelProvider(this, factory).get(HistoryViewModel::class.java)
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref, rootKey)

        //Finding the erase preference
        val deleteData :Preference? = findPreference("erase")

        //If the user clicks the erase preference wipe the database
        deleteData?.setOnPreferenceClickListener {
            historyViewModel.deleteAll()
            true
        }




    }

    

}
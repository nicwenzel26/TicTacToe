package com.csci448.a2.ui

import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.csci448.a2.R
import com.csci448.a2.data.HistoryDatabase
import java.util.concurrent.Executors

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
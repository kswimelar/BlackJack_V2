package edu.rvc.student.blackjack_v2

import android.R
import android.os.Bundle
import android.preference.PreferenceActivity


class PreferencesActivity : PreferenceActivity() {


    /** Called when the activity is first created.  */

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.prefs)

        // TODO Auto-generated method stub

    }


}
package com.app.lifehackstudiotestapp.ui.base

import androidx.fragment.app.Fragment

/**
* Интерфейс нафигации внутри [BaseFlowFragment] фрагмента
*/
interface IFlowNavigator {

    fun openFragment(fragment: Fragment)

    fun onBackPressed()
}
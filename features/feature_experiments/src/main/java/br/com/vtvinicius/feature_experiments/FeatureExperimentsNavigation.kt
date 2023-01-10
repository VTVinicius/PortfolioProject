package br.com.vtvinicius.feature_experiments

import androidx.navigation.NavController

interface FeatureExperimentsNavigation {

    fun goToLobby(navController: NavController)

    fun goToHomeFeature(navController: NavController)

    fun goToMotionTopBar(navController: NavController)
}
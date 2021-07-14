package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.payment.PaymentDialogFragment

class DealsActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.credit_card -> {
        val navHostFragment =
          supportFragmentManager.findFragmentById(R.id.deals_navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_global_to_payment)
//        val action = Deals
//        PaymentDialogFragment().show(supportFragmentManager, "CreditCardValidation")
        true
      }
      else -> false
    }
  }
}

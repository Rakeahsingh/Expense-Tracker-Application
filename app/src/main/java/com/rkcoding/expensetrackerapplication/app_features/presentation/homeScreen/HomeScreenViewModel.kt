package com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import com.rkcoding.expensetrackerapplication.app_features.domain.use_case.GetUseCases
import com.rkcoding.expensetrackerapplication.core.UiEvent
import com.rkcoding.expensetrackerapplication.utils.TabButton
import com.rkcoding.expensetrackerapplication.utils.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.YearMonth
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: FirebaseTransactionRepository,
    private val useCases: GetUseCases
): ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {

            repository.realTimeTransactionData()

            getTransaction()


        }
    }

    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnTabValueChange -> {
                _state.update {
                    it.copy(
                        tabButton = event.tabButton
                    )
                }
            }
        }
    }

   suspend fun getTransaction(){
        _state.update { it.copy(isLoading = true) }
        repository.transaction.collectLatest { transaction ->
            _state.update {
                it.copy(
                    transaction = transaction,
                    isLoading = false
                )
            }
            calculateTotalAmounts(transaction)
        }
    }


    private fun calculateTotalAmounts(transactions: List<Transaction>){
        val totalIncome = transactions.filter { it.transactionType == TransactionType.INCOME.value.toString() }
            .sumOf { it.transactionAmount }

        val totalExpense = transactions.filter { it.transactionType == TransactionType.EXPENSE.value.toString() }
            .sumOf { it.transactionAmount }

        val totalBalance = totalIncome - totalExpense

        _state.update {
            it.copy(
                totalBalance = totalBalance.toInt(),
                totalIncome = totalIncome.toInt(),
                totalExpense = totalExpense.toInt()
            )
        }
    }



//    fun fetchTodayAndMonthlyTransaction(){
//        viewModelScope.launch{
//            val transaction = when(_state.value.tabButton){
//                TabButton.TODAY -> useCases.todayTransactionUseCase.invoke()
//                TabButton.MONTHLY -> {
//                    val currentYearMonth = YearMonth.now()
//                    useCases.monthlyTransactionUseCase.invoke(currentYearMonth)
//                }
//            }
//
//            _state.update {
//                it.copy(
//                    transaction = transaction
//                )
//            }
//
//        }
//    }

    fun fetTodayTransaction(){
        viewModelScope.launch {
            val transaction = useCases.todayTransactionUseCase.invoke()

            _state.update {
                it.copy(
                    transaction = transaction
                )
            }
        }
    }

    fun fetMonthlyTransaction(){
        viewModelScope.launch {
            val currentYearMonth = YearMonth.now()
            val transaction = useCases.monthlyTransactionUseCase.invoke(currentYearMonth)

            _state.update {
                it.copy(
                    transaction = transaction
                )
            }
        }
    }



}
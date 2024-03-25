package com.belhiba.budget.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import java.util.UUID

class BudgetRepository {

    private val _transactions = MutableStateFlow<List<Transaction>>(
        listOf(
            Transaction(
                id = "tx-01",
                title = "Abonnement Transport / Recharge Véhicule",
                amount = 45.0,
                type = TransactionType.EXPENSE,
                category = ExpenseCategory.TRANSPORT
            ),
            Transaction(
                id = "tx-02",
                title = "Indemnité de Stage",
                amount = 1200.0,
                type = TransactionType.INCOME,
                category = ExpenseCategory.OTHER
            )
        )
    )
    val transactions: Flow<List<Transaction>> = _transactions.asStateFlow()

    val summary: Flow<BudgetSummary> = _transactions.map { list ->
        val income = list.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expense = list.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }
        BudgetSummary(totalIncome = income, totalExpense = expense)
    }

    suspend fun addTransaction(tx: Transaction) {
        val newTx = if (tx.id.isBlank()) tx.copy(id = UUID.randomUUID().toString().take(8)) else tx
        _transactions.value = listOf(newTx) + _transactions.value
    }

    suspend fun deleteTransaction(id: String) {
        _transactions.value = _transactions.value.filterNot { it.id == id }
    }
}

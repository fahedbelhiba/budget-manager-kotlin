package com.belhiba.budget.data

import java.util.Date

enum class TransactionType {
    INCOME, EXPENSE
}

enum class ExpenseCategory {
    TRANSPORT, HOUSING, FOOD, TECH_SERVICES, ENTERTAINMENT, OTHER
}

data class Transaction(
    val id: String = "",
    val title: String = "",
    val amount: Double = 0.0,
    val type: TransactionType = TransactionType.EXPENSE,
    val category: ExpenseCategory = ExpenseCategory.TRANSPORT,
    val date: Date = Date(),
    val notes: String = ""
)

data class BudgetSummary(
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val currentBalance: Double = totalIncome - totalExpense
)

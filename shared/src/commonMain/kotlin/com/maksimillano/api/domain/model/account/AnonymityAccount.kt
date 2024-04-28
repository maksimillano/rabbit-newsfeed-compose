package com.maksimillano.api.domain.model.account

data object AnonymityAccount : Account {
    override val number: Int = 0
}
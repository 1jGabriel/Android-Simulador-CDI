package com.br.basedroid.utils

class AmountMask(
    validation: ((Boolean) -> Unit)? = null
) : NumberMask("R$ ###########", validation = validation)

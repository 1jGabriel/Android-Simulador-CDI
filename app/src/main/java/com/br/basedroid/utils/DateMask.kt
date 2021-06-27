package com.br.basedroid.utils

class DateMask(
    validation: ((Boolean) -> Unit)? = null
) : NumberMask("##/##/####", validation = validation)

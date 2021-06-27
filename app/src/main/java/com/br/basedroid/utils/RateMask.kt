package com.br.basedroid.utils

class RateMask(
    validation: ((Boolean) -> Unit)? = null
) : NumberMask("##### %", validation = validation)

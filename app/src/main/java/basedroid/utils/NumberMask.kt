package basedroid.utils

import android.text.Editable
import android.text.TextWatcher

open class NumberMask(
    private val maskTemplate: String,
    private val maskAfterTemplate: String? = null,
    private val bulkInputMode: BulkInputMode = BulkInputMode.LEADING
) : TextWatcher {

    enum class BulkInputMode {
        LEADING,
        TRAILING
    }

    protected var selfChange = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // NOTHING HERE
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // NOTHING HERE
    }

    override fun afterTextChanged(string: Editable?) {
        if (selfChange) return

        selfChange {
            if (maskAfterTemplate != null &&
                string != null &&
                string.length > maskTemplate.length
            ) {
                mask(string, maskAfterTemplate)
            } else {
                mask(string, maskTemplate)
            }
        }
    }

    open fun mask(text: Editable?, maskTemplate: String) {
        if (text.isNullOrEmpty()) return

        normalizeText(text, maskTemplate)

        val editableFilters = text.filters
        text.filters = emptyArray()

        val formatted = StringBuilder()
        val list = text.toMutableList()

        maskTemplate.forEach { m ->
            if (list.isNullOrEmpty()) return@forEach

            var c = list[0]
            if (m.isPlaceHolder()) {
                if (!c.isLetterOrDigit()) {
                    val iterator = list.iterator()
                    while (iterator.hasNext()) {
                        c = iterator.next()
                        if (c.isLetterOrDigit()) break
                        iterator.remove()
                    }
                }
                if (list.isNullOrEmpty()) return@forEach

                formatted.append(c)
                list.removeAt(0)
            } else {
                formatted.append(m)
                if (m == c) {
                    list.removeAt(0)
                }
            }
        }
        val previousLength = text.length
        val currentLength = formatted.length
        text.replace(0, previousLength, formatted, 0, currentLength)

        text.filters = editableFilters
    }

    private fun normalizeText(text: Editable, maskTemplate: String) {
        val rawTextLength = rawTextLength(text)
        val rawMaskLength = rawMaskLength(maskTemplate)
        if (rawTextLength > rawMaskLength) {
            when (bulkInputMode) {
                BulkInputMode.LEADING -> text.delete(rawMaskLength, rawTextLength)
                BulkInputMode.TRAILING -> text.delete(0, rawTextLength - rawMaskLength)
            }
        }
    }

    private fun rawTextLength(text: Editable?): Int = text?.filter { it.isDigit() }?.length ?: 0

    private fun rawMaskLength(maskTemplate: String): Int = maskTemplate.filter { it == '#' }.length

    protected fun selfChange(modifier: () -> Unit) {
        selfChange = true
        modifier()
        selfChange = false
    }

    private fun Char.isPlaceHolder(): Boolean = this == '#'
}
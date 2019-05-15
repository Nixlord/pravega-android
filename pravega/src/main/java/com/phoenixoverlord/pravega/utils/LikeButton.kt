package com.phoenixoverlord.pravega.utils

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.widget.Checkable
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.phoenixoverlord.pravega.R

class LikeButton(context : Context, attributes: AttributeSet) : MaterialButton(context, attributes), Checkable {

    private var checked : Boolean = false
    private var onCheckedChange : ((likeButton : LikeButton, checked: Boolean) -> Unit)? = null

    override fun isChecked() = checked

    override fun setChecked(checked: Boolean) {
        this.checked = checked

        val colorAccent = ContextCompat.getColor(context, R.color.colorAccent)

        if (checked) {
            background.setColorFilter(colorAccent, PorterDuff.Mode.SRC)
            setTextColor(Color.WHITE)
            text = "Liked"
        }
        else {
            background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC)
            setTextColor(colorAccent)
            text = "Like"
        }

        onCheckedChange?.invoke(this, checked)
    }

    override fun toggle() {
        // Do not change into property access syntax
        setChecked(!checked)
    }

    fun setOnCheckedChangedListener(onCheckedChange : (likeButton : LikeButton, checked : Boolean) -> Unit) {
        this.onCheckedChange = onCheckedChange
        super.setOnClickListener { toggle() }
    }
}


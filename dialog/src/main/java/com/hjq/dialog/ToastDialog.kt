@file:Suppress("unused","RtlHardcoded")
package com.hjq.dialog

import android.view.Gravity
import androidx.fragment.app.FragmentActivity
import com.hjq.dialog.base.BaseDialog
import com.hjq.dialog.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_toast.view.*


/**
 * <p>文件描述：<p>
 * <p>@author 烤鱼<p>
 * <p>@date 2019/3/3 0003 <p>
 * <p>@update 2019/3/3 0003<p>
 * <p>版本号：1<p>
 *
 */
object ToastDialog {

    class Builder @JvmOverloads constructor(activity: FragmentActivity, themeResId: Int = -1) :
        BaseDialogFragment.Builder<Builder>(activity, themeResId), Runnable {

        private lateinit var mType: Type

        init {
            setContentView(R.layout.dialog_toast)
            setGravity(Gravity.CENTER)
            mAnimations = BaseDialog.AnimStyle.TOAST
            mCancelable = false
        }

        fun setType(type: Type): Builder {
            mType = type

            when (type) {
                ToastDialog.Type.FINISH -> mContentView.iv_dialog_toast_icon.setImageResource(R.mipmap.ic_dialog_tip_finish)
                ToastDialog.Type.ERROR -> mContentView.iv_dialog_toast_icon.setImageResource(R.mipmap.ic_dialog_tip_error)
                ToastDialog.Type.WARN -> mContentView.iv_dialog_toast_icon.setImageResource(R.mipmap.ic_dialog_tip_warning)
            }
            return this
        }

        fun setMessage(resId: Int): Builder {
            return setMessage(activity.getText(resId))
        }

        fun setMessage(text: CharSequence): Builder {
            mContentView.tv_dialog_toast_message.text = text
            return this
        }

        override fun show(): BaseDialog {

            // 如果内容为空就抛出异常
            if ("" == mContentView.tv_dialog_toast_message.text.toString()) {
                throw IllegalArgumentException("Dialog message not null")
            }
            // 延迟自动关闭
            mContentView.tv_dialog_toast_message.postDelayed(this, 3000)
            return super.show()
        }


        override fun run() {
            if (mDialog.isShowing) {
                dismiss()
            }
        }

    }

    /**
     * 显示的类型
     */
    enum class Type {
        // 完成，错误，警告
        FINISH,
        ERROR, WARN
    }
}
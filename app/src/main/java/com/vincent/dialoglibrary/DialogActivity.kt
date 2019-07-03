package com.vincent.dialoglibrary

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.hjq.dialog.*
import com.hjq.dialog.base.BaseDialog
import com.hjq.dialog.base.BaseDialogFragment
import kotlinx.android.synthetic.main.activity_dialog.*


 class DialogActivity : AppCompatActivity() {

    private lateinit var mToast: Toast
    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        initEvent()
        mToast = Toast.makeText(this,"",Toast.LENGTH_SHORT)
    }

    private fun initEvent(){
        btn_dialog_message.setOnClickListener {
            MessageDialog.Builder(this )
                .setTitle("我是标题") // 标题可以不用填写
                .setMessage("我是内容")
                .setConfirm("确定")
                .setCancel("取消") // 设置 null 表示不显示取消按钮
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .apply {
                    this.mListener = object : MessageDialog.OnListener {
                        override fun confirm(dialog: Dialog) {
                            toast("确定了")
                        }

                        override fun cancel(dialog: Dialog) {
                            toast("取消了")
                        }


                    }
                }

                .show()
        }
        btn_dialog_bottom_menu.setOnClickListener {
            val data = ArrayList<String>()
            for (i in 0..9) {
                data.add("我是数据$i")
            }
            MenuDialog.Builder(this)
                .setCancel("取消") // 设置 null 表示不显示取消按钮
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .setList(data)
                .setGravity(Gravity.BOTTOM)
                .apply {
                    mListener = object : MenuDialog.OnListener {
                        override fun select(dialog: Dialog, position: Int, text: String) {
                            toast("位置：$position，文本：$text")
                        }

                        override fun cancel(dialog: Dialog) {
                            toast("取消了")
                        }


                    }
                    mAnimations = BaseDialog.AnimStyle.BOTTOM
                }
                .show()
        }
        btn_dialog_center_menu.setOnClickListener {
            val data = ArrayList<String>()
            for (i in 0..9) {
                data.add("我是数据$i")
            }
            MenuDialog.Builder(this)
                .setCancel(null) // 设置 null 表示不显示取消按钮
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .setList(data)
                .setGravity(Gravity.CENTER)
                .apply {
                    mListener = object : MenuDialog.OnListener {
                        override fun select(dialog: Dialog, position: Int, text: String) {
                            toast("位置：$position，文本：$text")
                        }

                        override fun cancel(dialog: Dialog) {
                            toast("取消了")
                        }


                    }
                    mAnimations = BaseDialog.AnimStyle.SCALE
                }
                .show()
        }
        btn_dialog_succeed_toast.setOnClickListener {
            ToastDialog.Builder(this)
                .setType(ToastDialog.Type.FINISH)
                .setMessage("完成")
                .show()
        }
        btn_dialog_fail_toast.setOnClickListener {
            ToastDialog.Builder(this)
                .setType(ToastDialog.Type.ERROR)
                .setMessage("错误")
                .show()
        }
        btn_dialog_warn_toast.setOnClickListener {
            ToastDialog.Builder(this)
                .setType(ToastDialog.Type.WARN)
                .setMessage("警告")
                .show()
        }
        btn_dialog_wait.setOnClickListener {
            val dialog = WaitDialog.Builder(this)
                .setMessage("加载中...") // 消息文本可以不用填写
                .show()
            Handler(Looper.getMainLooper()).postDelayed({ dialog.dismiss() }, 3000)
        }
        btn_dialog_pay.setOnClickListener {
            PayPasswordDialog.Builder(this)
                .setTitle("请输入支付密码")
                .setSubTitle("用于购买一个女盆友")
                .setMoney("￥ 100.00")
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .apply {
                    this.mListener = object : PayPasswordDialog.OnListener {
                        override fun complete(dialog: Dialog, password: String) {
                            toast(password)
                        }

                        override fun cancel(dialog: Dialog) {
                            toast("取消了")
                        }
                    }
                }
                .show()
        }
        btn_dialog_date.setOnClickListener {
            DateDialog.Builder(this).apply { this.mListener = object : DateDialog.OnListener{
                override fun onSelected(dialog: Dialog, year: Int, month: Int, day: Int) {
                    toast("${year}年${month}月${day}日")
                }

                override fun onCancel(dialog: Dialog) {
                    toast("取消")
                }

            } }.setTitle("请选择日期").show()
        }
        btn_dialog_custom.setOnClickListener {

            Custom(this)
                .setContentView(R.layout.dialog_custom)
                //.setText(id, "我是预设置的文本")
                .setOnClickListener(R.id.btn_dialog_custom_ok, object : BaseDialog.OnClickListener<View> {
                    override fun onClick(dialog: Dialog, view: View) {
                        dialog.dismiss()
                    }

                })
                .apply {
                    this.mAnimations = BaseDialog.AnimStyle.SCALE
                }
                .show()


        }


    }

    class Custom @JvmOverloads constructor (activity: FragmentActivity, themeResId: Int = -1):
        BaseDialogFragment.Builder<Custom>(activity, themeResId)

    private fun toast(info:String?){
        info?:return
        mToast.setText(info)
        mToast.show()
    }


}

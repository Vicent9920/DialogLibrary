package com.vincent.dialoglibrary

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.hjq.dialog.*
import com.hjq.dialog.base.BaseDialog
import com.hjq.dialog.base.BaseDialogFragment
import kotlinx.android.synthetic.main.activity_dialog.*


class DialogActivity : AppCompatActivity() {

    lateinit var mToast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        initEvent()
        mToast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
    }

    private fun initEvent(){
        btn_dialog_message.setOnClickListener {
            MessageDialog.Builder(this)
                .setTitle("我是标题") // 标题可以不用填写
                .setMessage("我是内容")
                .setConfirm("确定")
                .setCancel("取消") // 设置 null 表示不显示取消按钮
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .setListener(object : MessageDialog.OnListener {
                    override fun confirm(dialog: Dialog?) {
                        toast("确定了")
                    }

                    override fun cancel(dialog: Dialog?) {
                        toast("取消了")
                    }
                })
                .show()
        }
        btn_dialog_bottom_menu.setOnClickListener {
            val data = mutableListOf<String>()
            for (i in 0..9) {
                data.add("我是数据$i")
            }
            MenuDialog.Builder(this)
                .setCancel("取消") // 设置 null 表示不显示取消按钮
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .setList(data)
                .setListener(object : MenuDialog.OnListener {
                    override fun select(dialog: Dialog?, position: Int, text: String?) {
                        toast("位置：$position，文本：$text")
                    }

                    override fun cancel(dialog: Dialog?) {
                        toast("取消了")
                    }

                })
                .setGravity(Gravity.BOTTOM)
                .setAnimStyle(BaseDialog.AnimStyle.BOTTOM)
                .show()
        }
        btn_dialog_center_menu.setOnClickListener {
            val data = mutableListOf<String>()
            for (i in 0..9) {
                data.add("我是数据$i")
            }
            MenuDialog.Builder(this)
                .setCancel(null) // 设置 null 表示不显示取消按钮
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .setList(data)
                .setListener(object : MenuDialog.OnListener {
                    override fun select(dialog: Dialog?, position: Int, text: String?) {
                        toast("位置：$position，文本：$text")
                    }

                    override fun cancel(dialog: Dialog?) {
                        toast("取消了")
                    }

                })
                .setGravity(Gravity.CENTER)
                .setAnimStyle(BaseDialog.AnimStyle.BOTTOM)
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
            val time:Long = 3000
            val dialog = WaitDialog.Builder(this)
                .setMessage("加载中...") // 消息文本可以不用填写
                .show()
            Handler().postDelayed({ dialog.dismiss() },time)
        }
        btn_dialog_pay.setOnClickListener {
            PayPasswordDialog.Builder(this)
                .setTitle("请输入支付密码")
                .setSubTitle("用于购买一个女盆友")
                .setMoney("￥ 100.00")
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .setListener(object : PayPasswordDialog.OnListener {
                    override fun complete(dialog: Dialog?, password: String?) {
                        toast(password)
                    }

                    override fun cancel(dialog: Dialog?) {
                        toast("取消了")
                    }

                })
                .show()
        }
        btn_dialog_date.setOnClickListener {

        }
        btn_dialog_custom.setOnClickListener {
            Custom(this)
                .setAnimStyle(BaseDialog.AnimStyle.SCALE)
                .setContentView(R.layout.dialog_custom)
                //.setText(id, "我是预设置的文本")
                .setOnClickListener(R.id.btn_dialog_custom_ok, object : BaseDialog.OnClickListener<View> {
                    override fun onClick(dialog: Dialog, view: View) {
                        dialog.dismiss()
                    }

                })
                .show()
        }
    }

    private fun toast(info:String?){
        info?:return
        mToast.setText(info)
        mToast.show()
    }

    class Custom(activity: FragmentActivity, themeResId: Int = -1):
        BaseDialogFragment.Builder<Custom>(activity, themeResId)
}

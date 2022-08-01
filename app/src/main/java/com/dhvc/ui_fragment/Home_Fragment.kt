package com.dhvc.ui_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dhvc.R
import com.dhvc.adapter.*
import com.dhvc.databinding.FragmentHomeBinding
import com.dhvc.toalbase.BaseFragment
import com.google.android.material.tabs.TabLayout
import android.view.*
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.dhvc.Scan.Scan_BlankFragment
import com.dhvc.Scan.Scancarrier


val messages = listOf(
    "Mo Xiao farmer bacon hun, bumper harvest guest foot chicken dolphin.  Mountains and rivers doubt no way, and a village.  Xiao Drum follows the spring society, simple and ancient style.  The conical staves tap their night from now on ",
    "伤心尽处露笑颜，醉里孤单写狂欢。两路殊途情何奈，三千弱水忧忘川。花开彼岸朦胧色，月过长空爽朗天。青鸟思飞无侧羽，重山万水亦徒然",
    "Ronghua dream, fame paper half a piece, non sea wave thousands of feet, horse hoofs trampled forbidden street frost, listen to a few degrees of the first chicken sing  ",
    "莫笑农家腊酒浑，丰年留客足鸡豚。山重水复疑无路，柳暗花明又一村。箫鼓追随春社近，衣冠简朴古风存。从今若许闲乘月，拄杖无时夜叩门",
    "Call back, west Lake mountain wild ape mourning.  Twenty years how many romantic strange, flowers fall flowers open.  Wang Yunxiao will worship Taiwan, sleeve star anbang policy, broken smoke month ecstasy village.  Sour Chai laughs at me, and I laugh at sour Chai  ",
    "又到绿杨曾折处，不语垂鞭，踏遍清秋路。衰草连天无意绪，雁声远向萧关去。恨天涯行役苦，只恨西风，吹梦成今古。明日客程还几许，沾衣况是新寒雨"
)

val data = listOf(
    "Mo Xiao farmer bacon hun, bumper harvest guest foot chicken dolphin",
    "莫笑农家腊酒浑",
    "恨天涯行役苦",
    "衣冠简朴古风存",
    "柳暗花明又一村",
    "明日客程还几许",
    "又到绿杨曾折处",
    "Call back, west Lake mountain wild ape mourning. ",
    "三千弱水忧忘川。花开彼岸朦胧色，月过长空爽朗天。青鸟思飞无侧羽，",
    "踏遍清秋路",
    "。两路殊途情何奈，三千弱水忧忘川。花",
    "mountain wild ape mourning. ",
    "吹梦成今古",
    "衰草连天无意绪，雁声远向萧关去。恨天涯行役苦，只恨西风，吹梦成今古。",
)

val title = listOf(
    "天猫U先",
    "今日爆款",
    "芭比农场",
    "飞猪旅行",
    "饿了么",
    "鱼村吃货",
    "鱼票票",
    "土货鲜食",
    "天猫好房",
    "充值中心",
    "陶鲜达",
    "领鱼金币",
    "阿里拍卖",
    "分类",
    "天猫国际",
    "咸鱼",
    "鱼鱼好药",
    "天猫汽车",
    "鱼鱼家电",
    "更多频道"
)
class Home_Fragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home_) {
    override fun observeViewModel() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        val viewpager = mDataBinding.root.findViewById<ViewPager>(R.id.viewpager)
        val tab = mDataBinding.root.findViewById<TabLayout>(R.id.tab)

        val list = ArrayList<Fragment>()
        list.add(Subscribefor_BlankFragment())
        list.add(Recommend_Fragment())
        val adapter =  VpAdapter(list = list,fragment = childFragmentManager)
        viewpager.adapter =adapter
        viewpager.currentItem = 2
        viewpager.offscreenPageLimit=2
        tab.setupWithViewPager(mDataBinding.viewpager)
        tab.getTabAt(0)?.text="订阅"
        tab.getTabAt(1)?.text="推荐"

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_refresh,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
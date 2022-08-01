package com.dhvc.ui_activity.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dhvc.R


val messages = listOf(
    "Mo Xiao farmer bacon hun, bumper harvest guest foot chicken dolphin.  Mountains and rivers doubt no way, and a village.  Xiao Drum follows the spring society, simple and ancient style.  The conical staves tap their night from now on ",
    "伤心尽处露笑颜，醉里孤单写狂欢。两路殊途情何奈，三千弱水忧忘川。花开彼岸朦胧色，月过长空爽朗天。青鸟思飞无侧羽，重山万水亦徒然",
    "Ronghua dream, fame paper half a piece, non sea wave thousands of feet, horse hoofs trampled forbidden street frost, listen to a few degrees of the first chicken sing  ",
    "莫笑农家腊酒浑，丰年留客足鸡豚。山重水复疑无路，柳暗花明又一村。箫鼓追随春社近，衣冠简朴古风存。从今若许闲乘月，拄杖无时夜叩门",
    "Call back, west Lake mountain wild ape mourning.  Twenty years how many romantic strange, flowers fall flowers open.  Wang Yunxiao will worship Taiwan, sleeve star anbang policy, broken smoke month ecstasy village.  Sour Chai laughs at me, and I laugh at sour Chai  ",
    "又到绿杨曾折处，不语垂鞭，踏遍清秋路。衰草连天无意绪，雁声远向萧关去。恨天涯行役苦，只恨西风，吹梦成今古。明日客程还几许，沾衣况是新寒雨"
)
class Circle_MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Conversation1(message = messages)
        }
    }

}
@Composable
fun Conversation1(message: List<String>){
    LazyColumn{
        items(message){
            it ->   MessageCard(s = it)
        }
    }
}



@Composable
fun MessageCard(s: String){
        Surface(shape = MaterialTheme.shapes.medium,
        elevation = 5.dp, modifier = Modifier.padding(all = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(all = 8.dp)
        ){
            Image(
                painterResource(id = R.mipmap.ic_launcher),
                contentDescription = "这是啥呀",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, shape = CircleShape)

            )
            Spacer(modifier = Modifier.padding(horizontal = 5.dp,vertical = 4.dp))
            Column() {
                Text(text = s,
                    color = MaterialTheme.colors.secondaryVariant
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Text(text = s)
            }
        }
    }
//
}
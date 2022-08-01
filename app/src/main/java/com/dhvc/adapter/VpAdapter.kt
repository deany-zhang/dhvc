package com.dhvc.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class VpAdapter (var list: List<Fragment>, fragment: FragmentManager?) :
        FragmentStatePagerAdapter(fragment!!) {
        override fun getItem(position: Int): Fragment {
            return list[position]
        }

        override fun getCount(): Int {
            return list.size
        }
    }

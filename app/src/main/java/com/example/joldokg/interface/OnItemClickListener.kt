package com.example.joldokg.`interface`

import com.example.joldokg.data.models.DetailVideo

interface OnItemClickListener {

        fun itemClick(position: Int)

        fun itemClick(model: DetailVideo)

}
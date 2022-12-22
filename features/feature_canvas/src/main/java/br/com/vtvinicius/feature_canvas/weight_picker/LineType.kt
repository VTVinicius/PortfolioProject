package br.com.vtvinicius.feature_canvas.weight_picker

sealed class LineType {
    object Normal : LineType()
    object FiveStep : LineType()
    object TenStep : LineType()
}
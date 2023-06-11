package com.example.cotizacion
import kotlin.random.Random

class Cotizacion(
    private var folio: Int,
    private var descripcion: String,
    var valorAuto: Double,
    var porEnganche: Double,
    var plazo: Int
) {

    fun getFolio(): Int {
        return folio
    }

    fun setFolio(folio: Int) {
        this.folio = folio
    }

    fun getDescripcion(): String {
        return descripcion
    }

    fun setDescripcion(descripcion: String) {
        this.descripcion = descripcion
    }

    fun getValorAuto(): Double {
        return valorAuto
    }

    fun setValorAuto(valorAuto: Double) {
        this.valorAuto = valorAuto
    }

    fun getPorEnganche(): Double {
        return porEnganche
    }

    fun setPorEnganche(porEnganche: Double) {
        this.porEnganche = porEnganche
    }

    fun getPlazo(): Int {
        return plazo
    }

    fun setPlazo(plazo: Int) {
        this.plazo = plazo
    }

    fun calcularEnganche(): Double {
        val porcentaje: Double = this.getPorEnganche() / 100
        val enganche: Double = porcentaje * this.valorAuto
        return enganche
    }

    fun calcularPagoMensual(): Double {
        val pago: Double = this.getValorAuto() - this.calcularEnganche()
        var mensual = 0.0
        when (this.getPlazo()) {
            1 -> mensual = pago / 12
            2 -> mensual = pago / 18
            3 -> mensual = pago / 24
            4 -> mensual = pago / 36
        }
        return mensual
    }
}

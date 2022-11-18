package no.nav.familie.eksterne.kontrakter.bisys

import java.time.LocalDate

data class BarnetilsynBisysResponse(
    val barnetilsynBisysPerioder: List<BarnetilsynBisysPeriode>
)

data class BarnetilsynBisysPeriode(
    val periode: Periode,
    val barnIdenter: List<String>
)

data class BarnetilsynBisysRequest(
    val ident: String,
    val fomDato: LocalDate
)

data class Periode(
    val fom: LocalDate,
    val tom: LocalDate
)

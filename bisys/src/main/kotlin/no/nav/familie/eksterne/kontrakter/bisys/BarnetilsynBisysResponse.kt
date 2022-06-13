package no.nav.familie.eksterne.kontrakter.bisys

import java.time.LocalDate

data class BarnetilsynBisysResponse(
    val barnetilsynBisysPerioder: List<BarnetilsynBisysPeriode>
)

data class BarnetilsynBisysPeriode(
    val periode: Periode,
    val barnIdenter: List<String>,
    val månedsbeløp: Int,
    val datakilde: Datakilde
)

data class BarnetilsynBisysRequest(
    val ident: String,
    val fomDato: LocalDate
)

enum class Datakilde {
    EF,
    INFOTRYGD
}

data class Periode(
    val fom: LocalDate,
    val tom: LocalDate
)

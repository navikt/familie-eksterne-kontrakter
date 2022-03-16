package no.nav.familie.eksterne.kontrakter.bisys

import java.time.LocalDate

class BarnetilsynBisysResponse(
    val gjeldendeBarnIdent: String,
    val periode: Periode,
    val beløp: Int,
    val datakilde: Datakilde
)

enum class Datakilde {
    EF,
    INFOTRYGD
}

data class Periode(
    val fom: LocalDate,
    val tom: LocalDate
)
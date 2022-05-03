package no.nav.familie.eksterne.kontrakter.bisys

import java.time.LocalDate

class BarnetilsynBisys(
        val gjeldendeBarnIdent: String,
        val periode: Periode,
        val prosentsats: Int,
        val beløp: Int,
        val datakilde: Datakilde
)

class BarnetilsynBisysResponse(
        val BarnetilsynBisysListe: List<BarnetilsynBisys>
)

class BarnetilsynBisysRequest(
        val BarnetilsynBisysListe: List<BarnetilsynBisys>
)

enum class Datakilde {
    EF,
    INFOTRYGD
}

data class Periode(
        val fom: LocalDate,
        val tom: LocalDate
)
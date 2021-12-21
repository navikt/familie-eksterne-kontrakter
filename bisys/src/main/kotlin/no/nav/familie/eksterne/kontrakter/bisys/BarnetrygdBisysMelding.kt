package no.nav.familie.eksterne.kontrakter.bisys

import java.time.YearMonth

enum class BarnetrygdEndretType {
    RO, // Revurdering og Opphør
    RR, // Revurdering og Reduksjon
}

data class BarnEndretOpplysning(
    val ident: String,
    val årsakskode: BarnetrygdEndretType,
    val fom: YearMonth,
)

data class BarnetrygdBisysMelding(
    val søker: String,
    val barn: List<BarnEndretOpplysning>
)

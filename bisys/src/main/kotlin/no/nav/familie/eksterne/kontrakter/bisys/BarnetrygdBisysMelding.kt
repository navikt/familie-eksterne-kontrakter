package no.nav.familie.eksterne.kontrakter.bisys

import com.fasterxml.jackson.annotation.JsonAutoDetect
import java.time.YearMonth

enum class BarnetrygdEndretType {
    RO, // Revurdering og Opphør
    RR, // Revurdering og Reduksjon
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class BarnEndretOpplysning(
    val ident: String,
    val årsakskode: BarnetrygdEndretType,
    val fom: YearMonth,
)

data class BarnetrygdBisysMelding(
    val søker: String,
    val barn: List<BarnEndretOpplysning>,
)

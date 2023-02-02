package no.nav.familie.eksterne.kontrakter.arbeidsoppfolging

import java.time.LocalDate

data class VedtakOvergangsstønadArbeidsoppfølging(
    val vedtakId: Long,
    val personIdent: String,
    val barn: List<Barn>,
    val stønadstype: String,
    val periode: List<Periode>,
    val vedtaksresultat: String
)

data class Barn(
    val fødselsnummer: String? = null,
    val termindato: LocalDate? = null
)

data class Periode(
    val fom: LocalDate,
    val tom: LocalDate,
    val periodetype: String,
    val aktivitetstype: String,
)

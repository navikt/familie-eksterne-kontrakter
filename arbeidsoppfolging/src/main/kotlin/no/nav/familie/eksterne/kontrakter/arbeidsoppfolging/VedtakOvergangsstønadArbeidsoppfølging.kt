package no.nav.familie.eksterne.kontrakter.arbeidsoppfolging

import java.time.LocalDate

data class VedtakOvergangsstønadArbeidsoppfølging(
    val personIdent: String,
    val barn: List<Barn>,
    val stønadstype: String,
    val periode: List<Periode>
)

data class Barn(
    val fødselsnummer: String,
    val termindato: LocalDate
)

data class Periode(
    val fom: LocalDate,
    val tom: LocalDate,
    val vedtaksperiodeType: String,
    val aktivitetstype: String,
)

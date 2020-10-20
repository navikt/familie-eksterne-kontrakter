package no.nav.familie.eksterne.kontrakter

import java.time.LocalDate
import java.time.LocalDateTime

data class VedtakDVH(val fagsakId: String,
                     val behandlingsId: String,
                     val tidspunktVedtak: LocalDateTime,
                     val person: PersonDVH,
                     val ensligForsørger: Boolean,
                     val kategori: Kategori,
                     val underkategori: Underkategori,
                     val behandlingType: BehandlingType,
                     val behandlingOpprinnelse: BehandlingOpprinnelse,
                     val utbetalingsperioder: List<UtbetalingsperiodeDVH>)

data class UtbetalingsperiodeDVH(val hjemmel: String,
                                 val utbetaltPerMnd: Int,
                                 val stønadFom: LocalDate,
                                 val stønadTom: LocalDate,
                                 val utbetalingsDetaljer: List<UtbetalingsDetaljDVH>)

data class UtbetalingsDetaljDVH(val person: PersonDVH,
                                val klassekode: String,
                                val utbetaltPrMnd: Int,
                                val delytelseId: String)


data class PersonDVH(val personIdent: String,
                     val rolle: String,
                     val statsborgerskap: List<String>,
                     val bostedsland: String,
                     val primærland: String,
                     val sekundærland: String,
                     val delingsprosentOmsorg: Int,
                     val delingsprosentYtelse: Int,
                     val annenpartPersonident: String,
                     val annenpartStatsborgerskap: String,
                     val annenpartBostedsland: String)


enum class BehandlingOpprinnelse {
    MANUELL,
    AUTOMATISK_VED_FØDSELSHENDELSE,
    AUTOMATISK_VED_JOURNALFØRING
}

enum class BehandlingType(val visningsnavn: String) {
    FØRSTEGANGSBEHANDLING("Førstegangsbehandling"),
    REVURDERING("Revurdering"),
    MIGRERING_FRA_INFOTRYGD("Migrering fra infotrygd"),
    KLAGE("Klage"),
    MIGRERING_FRA_INFOTRYGD_OPPHØRT("Opphør migrering fra infotrygd"),
    TEKNISK_OPPHØR("Teknisk opphør")
}

enum class Kategori {
    EØS,
    NASJONAL
}

enum class Underkategori {
    UTVIDET,
    ORDINÆR
}
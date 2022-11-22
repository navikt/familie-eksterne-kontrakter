package no.nav.familie.eksterne.kontrakter

import java.time.LocalDate
import java.time.ZonedDateTime

data class VedtakDVH(
    val fagsakId: String,
    val behandlingsId: String,
    val tidspunktVedtak: ZonedDateTime,
    val person: PersonDVH,
    val kategori: Kategori,
    val behandlingType: BehandlingType,
    val utbetalingsperioder: List<UtbetalingsperiodeDVH>,
    val funksjonellId: String,
    val behandlingÅrsak: BehandlingÅrsak,
)

data class UtbetalingsperiodeDVH(
    val hjemmel: String,
    val utbetaltPerMnd: Int,
    val stønadFom: LocalDate,
    val stønadTom: LocalDate,
    val utbetalingsDetaljer: List<UtbetalingsDetaljDVH>
)

data class UtbetalingsDetaljDVH(
    val person: PersonDVH,
    val klassekode: String,
    val utbetaltPrMnd: Int,
    val delytelseId: String
)

data class PersonDVH(
    val personIdent: String,
    val rolle: String,
    val statsborgerskap: List<String>,
    val bostedsland: String,
    val delingsprosentYtelse: Int
)

enum class SøkersAktivitet {
    ARBEIDER,
    SELVSTENDIG_NÆRINGSDRIVENDE,
    MOTTAR_UTBETALING_SOM_ERSTATTER_LØNN,
    UTSENDT_ARBEIDSTAKER_FRA_NORGE,
    MOTTAR_UFØRETRYGD,
    MOTTAR_PENSJON,
    ARBEIDER_PÅ_NORSKREGISTRERT_SKIP,
    ARBEIDER_PÅ_NORSK_SOKKEL,
    ARBEIDER_FOR_ET_NORSK_FLYSELSKAP,
    ARBEIDER_VED_UTENLANDSK_UTENRIKSSTASJON,
    MOTTAR_UTBETALING_FRA_NAV_UNDER_OPPHOLD_I_UTLANDET,
    MOTTAR_UFØRETRYGD_FRA_NAV_UNDER_OPPHOLD_I_UTLANDET,
    MOTTAR_PENSJON_FRA_NAV_UNDER_OPPHOLD_I_UTLANDET,
    INAKTIV
}

enum class AnnenForeldersAktivitet {
    I_ARBEID,
    MOTTAR_UTBETALING_SOM_ERSTATTER_LØNN,
    FORSIKRET_I_BOSTEDSLAND,
    MOTTAR_PENSJON,
    INAKTIV,
    IKKE_AKTUELT
}

enum class BehandlingType(val visningsnavn: String) {
    FØRSTEGANGSBEHANDLING("Førstegangsbehandling"),
    REVURDERING("Revurdering"),
    TEKNISK_ENDRING("Teknisk endring")
}

enum class BehandlingÅrsak(val visningsnavn: String) {
    SØKNAD("Søknad"),
    ÅRLIG_KONTROLL("Årsak kontroll"),
    DØDSFALL("Dødsfall bruker"),
    NYE_OPPLYSNINGER("Nye opplysninger"),
    KLAGE("Klage"),
    TEKNISK_ENDRING("Teknisk endring"),
    KORREKSJON_VEDTAKSBREV("Korrigere vedtak med egen brevmal"),
    SATSENDRING("Satsendring"),
}

enum class Kategori {
    EØS,
    NASJONAL
}

package no.nav.familie.eksterne.kontrakter

import java.time.LocalDate
import java.time.ZonedDateTime

data class VedtakDVHV2(
    val fagsakId: String,
    val behandlingsId: String,
    val tidspunktVedtak: ZonedDateTime,
    val personV2: PersonDVHV2,
    val kategoriV2: KategoriV2,
    val underkategoriV2: UnderkategoriV2,
    val behandlingTypeV2: BehandlingTypeV2,
    val utbetalingsperioderV2: List<UtbetalingsperiodeDVHV2>,
    val funksjonellId: String,
    val behandlingÅrsakV2: BehandlingÅrsakV2,
)

data class UtbetalingsperiodeDVHV2(
    val hjemmel: String,
    val utbetaltPerMnd: Int,
    val stønadFom: LocalDate,
    val stønadTom: LocalDate,
    val utbetalingsDetaljer: List<UtbetalingsDetaljDVHV2>
)

data class UtbetalingsDetaljDVHV2(
    val person: PersonDVHV2,
    val klassekode: String,
    val utbetaltPrMnd: Int,
    val delytelseId: String
)

data class PersonDVHV2(
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

enum class BehandlingTypeV2(val visningsnavn: String) {
    FØRSTEGANGSBEHANDLING("Førstegangsbehandling"),
    REVURDERING("Revurdering"),
    TEKNISK_ENDRING("Teknisk endring")
}

enum class BehandlingÅrsakV2(val visningsnavn: String) {
    SØKNAD("Søknad"),
    FØDSELSHENDELSE("Fødselshendelse"),
    ÅRLIG_KONTROLL("Årsak kontroll"),
    DØDSFALL("Dødsfall bruker"),
    NYE_OPPLYSNINGER("Nye opplysninger"),
    KLAGE("Klage"),
    TEKNISK_ENDRING("Teknisk endring"),
    KORREKSJON_VEDTAKSBREV("Korrigere vedtak med egen brevmal"),
    SATSENDRING("Satsendring"),
}

enum class KategoriV2 {
    EØS,
    NASJONAL
}

enum class UnderkategoriV2 {
    ORDINÆR,
}

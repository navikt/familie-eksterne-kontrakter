package no.nav.familie.eksterne.kontrakter

import java.time.LocalDate
import java.time.YearMonth
import java.time.ZonedDateTime

data class VedtakDVHV2(
    val fagsakId: String,
    val behandlingsId: String,
    val tidspunktVedtak: ZonedDateTime,
    val personV2: PersonDVHV2,
    val ensligForsørger: Boolean,
    val kategoriV2: KategoriV2,
    @Deprecated("Mer riktig å se på ytelseType på utebetalingsdetalj ") val underkategoriV2: UnderkategoriV2?,
    val behandlingTypeV2: BehandlingTypeV2,
    val utbetalingsperioderV2: List<UtbetalingsperiodeDVHV2>,
    val kompetanseperioder: List<Kompetanse>? = null,
    val funksjonellId: String,
    val behandlingÅrsakV2: BehandlingÅrsakV2,
    val fagsakType: FagsakType? = null
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
    val delytelseId: String,
    val ytelseType: YtelseType?
)

data class PersonDVHV2(
    val personIdent: String,
    val rolle: String,
    val statsborgerskap: List<String>,
    val bostedsland: String,
    val delingsprosentYtelse: Int
)

data class Kompetanse(
    val barnsIdenter: List<String>,
    val fom: YearMonth,
    val tom: YearMonth?,
    val sokersaktivitet: KompetanseAktivitet? = null,
    val sokersAktivitetsland: String? = null,
    val annenForeldersAktivitet: KompetanseAktivitet? = null,
    val annenForeldersAktivitetsland: String? = null,
    val barnetsBostedsland: String? = null,
    val resultat: KompetanseResultat? = null,
    val annenForelderOmfattetAvNorskLovgivning: Boolean? = false,
)

@Deprecated("Skal bruke KompetanseAktivitet")
enum class SøkersAktivitet {
    @Deprecated("Skal bruke ARBEIDER. Siste melding sendt 2022-09-05 med offset 27648")
    ARBEIDER_I_NORGE,
    ARBEIDER,

    SELVSTENDIG_NÆRINGSDRIVENDE,

    @Deprecated("Skal bruke MOTTAR_UTBETALING_SOM_ERSTATTER_LØNN, Sist sendt 2022-08-04 med offset 18122")
    MOTTAR_UTBETALING_FRA_NAV_SOM_ERSTATTER_LØNN,
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

@Deprecated("Skal bruke KompetanseAktivitet")
enum class AnnenForeldersAktivitet {
    I_ARBEID,
    MOTTAR_UTBETALING_SOM_ERSTATTER_LØNN,
    FORSIKRET_I_BOSTEDSLAND,
    MOTTAR_PENSJON,
    INAKTIV,
    IKKE_AKTUELT,
    UTSENDT_ARBEIDSTAKER
}

enum class KompetanseAktivitet {
    ARBEIDER,
    SELVSTENDIG_NÆRINGSDRIVENDE,
    UTSENDT_ARBEIDSTAKER_FRA_NORGE,
    MOTTAR_UFØRETRYGD,
    ARBEIDER_PÅ_NORSKREGISTRERT_SKIP,
    ARBEIDER_PÅ_NORSK_SOKKEL,
    ARBEIDER_FOR_ET_NORSK_FLYSELSKAP,
    ARBEIDER_VED_UTENLANDSK_UTENRIKSSTASJON,
    MOTTAR_UTBETALING_FRA_NAV_UNDER_OPPHOLD_I_UTLANDET,
    MOTTAR_UFØRETRYGD_FRA_NAV_UNDER_OPPHOLD_I_UTLANDET,
    MOTTAR_PENSJON_FRA_NAV_UNDER_OPPHOLD_I_UTLANDET,
    MOTTAR_UTBETALING_SOM_ERSTATTER_LØNN,
    MOTTAR_PENSJON,
    INAKTIV,
    I_ARBEID,
    FORSIKRET_I_BOSTEDSLAND,
    IKKE_AKTUELT,
    UTSENDT_ARBEIDSTAKER,
}

enum class KompetanseResultat {
    NORGE_ER_PRIMÆRLAND,
    NORGE_ER_SEKUNDÆRLAND,
    TO_PRIMÆRLAND
}

enum class BehandlingTypeV2(val visningsnavn: String) {
    FØRSTEGANGSBEHANDLING("Førstegangsbehandling"),
    REVURDERING("Revurdering"),
    MIGRERING_FRA_INFOTRYGD("Migrering fra infotrygd"),
    MIGRERING_FRA_INFOTRYGD_OPPHØRT("Opphør migrering fra infotrygd"),
    TEKNISK_ENDRING("Teknisk endring")
}

enum class BehandlingÅrsakV2(val visningsnavn: String) {

    SØKNAD("Søknad"),
    FØDSELSHENDELSE("Fødselshendelse"),
    ÅRLIG_KONTROLL("Årsak kontroll"),
    DØDSFALL_BRUKER("Dødsfall bruker"),
    NYE_OPPLYSNINGER("Nye opplysninger"),
    KLAGE("Klage"),
    TEKNISK_ENDRING("Teknisk endring"),
    KORREKSJON_VEDTAKSBREV("Korrigere vedtak med egen brevmal"),
    OMREGNING_6ÅR("Omregning 6 år"),
    OMREGNING_18ÅR("Omregning 18 år"),
    OMREGNING_SMÅBARNSTILLEGG("Omregning småbarnstillegg"),
    SMÅBARNSTILLEGG("Småbarnstillegg"),
    SMÅBARNSTILLEGG_ENDRING_FRAM_I_TID("Småbarnstillegg endring fram i tid"),
    MIGRERING("Migrering"),
    SATSENDRING("Satsendring"),
    ENDRE_MIGRERINGSDATO("Endre migreringsdato"),
    HELMANUELL_MIGRERING("Manuell migrering"),
    MÅNEDLIG_VALUTAJUSTERING("Månedlig valutajustering"),
    NY_UTVIDET_KLASSEKODE("Ny utvidet klassekode")
}

enum class KategoriV2 {
    EØS,
    NASJONAL
}

enum class UnderkategoriV2 {
    UTVIDET,
    ORDINÆR,
    INSTITUSJON
}

enum class FagsakType {
    NORMAL,
    BARN_ENSLIG_MINDREÅRIG,
    INSTITUSJON
}

enum class YtelseType {
    ORDINÆR_BARNETRYGD,
    UTVIDET_BARNETRYGD,
    SMÅBARNSTILLEGG,
    MANUELL_VURDERING
}

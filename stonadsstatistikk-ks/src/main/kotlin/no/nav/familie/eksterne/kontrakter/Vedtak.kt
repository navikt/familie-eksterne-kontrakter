package no.nav.familie.eksterne.kontrakter

import java.math.BigDecimal
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZonedDateTime

data class VedtakDVH(
    val fagsakId: String,
    val behandlingsId: String,
    val tidspunktVedtak: ZonedDateTime,
    val person: PersonDVH,
    val kategori: Kategori,
    val behandlingType: BehandlingType,
    val utbetalingsperioder: List<UtbetalingsperiodeDVH>,
    val kompetanseperioder: List<Kompetanse>? = null,
    val funksjonellId: String,
    val behandlingÅrsak: BehandlingÅrsak,
    val vilkårResultater: List<VilkårResultat>? = emptyList(),
)

data class UtbetalingsperiodeDVH(
    val hjemmel: String,
    val utbetaltPerMnd: Int,
    val stønadFom: LocalDate,
    val stønadTom: LocalDate,
    val utbetalingsDetaljer: List<UtbetalingsDetaljDVH>,
)

data class UtbetalingsDetaljDVH(
    val person: PersonDVH,
    val klassekode: String,
    val utbetaltPrMnd: Int,
    val delytelseId: String,
)

data class PersonDVH(
    val personIdent: String,
    val rolle: String,
    val statsborgerskap: List<String>,
    val bostedsland: String,
    val delingsprosentYtelse: Int,
)

data class VilkårResultat(
    var resultat: Resultat,
    val antallTimer: BigDecimal? = null,
    val periodeFom: LocalDate? = null,
    val periodeTom: LocalDate? = null,
    val ident: String? = null,
    val vilkårType: Vilkår,
)

data class Kompetanse(
    val barnsIdenter: List<String>,
    val fom: YearMonth,
    val tom: YearMonth?,
    val kompetanseAktivitet: KompetanseAktivitet? = null,
    val sokersAktivitetsland: String? = null,
    val annenForeldersAktivitet: KompetanseAktivitet? = null,
    val annenForeldersAktivitetsland: String? = null,
    val barnetsBostedsland: String? = null,
    val resultat: KompetanseResultat? = null,
    val annenForelderOmfattetAvNorskLovgivning: Boolean? = false,
)

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
enum class BehandlingType(val visningsnavn: String) {
    FØRSTEGANGSBEHANDLING("Førstegangsbehandling"),
    REVURDERING("Revurdering"),
    TEKNISK_ENDRING("Teknisk endring"),
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
    BARNEHAGELISTE("Barnehageliste"),
    LOVENDRING_2024("Lovendring 2024"),
    IVERKSETTE_KA_VEDTAK("Iverksette KA-vedtak"),
}

enum class Kategori {
    EØS,
    NASJONAL,
}

enum class Resultat {
    OPPFYLT,
    IKKE_AKTUELT,
    IKKE_OPPFYLT,
    IKKE_VURDERT,
}

enum class Vilkår(
    val parterDetteGjelderFor: List<PersonType>,
    val ytelseType: YtelseType,
    val beskrivelse: String,
    val harRegelverk: Boolean,
    val eøsSpesifikt: Boolean,
) {

    BOSATT_I_RIKET(
        parterDetteGjelderFor = listOf(PersonType.SØKER, PersonType.BARN),
        ytelseType = YtelseType.ORDINÆR_KONTANTSTØTTE,
        beskrivelse = "Bosatt i riket",
        harRegelverk = true,
        eøsSpesifikt = false,
    ),
    LOVLIG_OPPHOLD(
        parterDetteGjelderFor = listOf(PersonType.SØKER),
        ytelseType = YtelseType.ORDINÆR_KONTANTSTØTTE,
        beskrivelse = "Lovlig opphold",
        harRegelverk = false,
        eøsSpesifikt = true,
    ),
    MEDLEMSKAP(
        parterDetteGjelderFor = listOf(PersonType.SØKER),
        ytelseType = YtelseType.ORDINÆR_KONTANTSTØTTE,
        beskrivelse = "Medlemskap",
        harRegelverk = true,
        eøsSpesifikt = false,
    ),
    BARNEHAGEPLASS(
        parterDetteGjelderFor = listOf(PersonType.BARN),
        ytelseType = YtelseType.ORDINÆR_KONTANTSTØTTE,
        beskrivelse = "Barnehageplass",
        harRegelverk = false,
        eøsSpesifikt = false,
    ),
    MEDLEMSKAP_ANNEN_FORELDER(
        parterDetteGjelderFor = listOf(PersonType.BARN),
        ytelseType = YtelseType.ORDINÆR_KONTANTSTØTTE,
        beskrivelse = "Medlemskap annen forelder",
        harRegelverk = true,
        eøsSpesifikt = false,
    ),
    BOR_MED_SØKER(
        parterDetteGjelderFor = listOf(PersonType.BARN),
        ytelseType = YtelseType.ORDINÆR_KONTANTSTØTTE,
        beskrivelse = "Bor fast hos søker",
        harRegelverk = true,
        eøsSpesifikt = false,
    ),
    BARNETS_ALDER(
        parterDetteGjelderFor = listOf(PersonType.BARN),
        ytelseType = YtelseType.ORDINÆR_KONTANTSTØTTE,
        beskrivelse = "Mellom 1 og 2 år eller adoptert",
        harRegelverk = false,
        eøsSpesifikt = false,
    ),
    ;

    enum class PersonType {
        SØKER,
        BARN,
    }

    enum class YtelseType(val klassifisering: String) {
        ORDINÆR_KONTANTSTØTTE("KS"), // TODO verdien må avklares med økonomi
    }
}

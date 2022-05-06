package no.nav.familie.eksterne.kontrakter

import java.time.LocalDate
import java.time.YearMonth
import java.time.ZonedDateTime

data class VedtakDVHV2(val fagsakId: String,
                       val behandlingsId: String,
                       val tidspunktVedtak: ZonedDateTime,
                       val personV2: PersonDVHV2,
                       val ensligForsørger: Boolean,
                       val kategoriV2: KategoriV2,
                       val underkategoriV2: UnderkategoriV2,
                       val behandlingTypeV2: BehandlingTypeV2,
                       val utbetalingsperioderV2: List<UtbetalingsperiodeDVHV2>,
                       val kompetanseperioder: List<Kompetanse>? = null,
                       val funksjonellId: String,
                       val behandlingÅrsakV2: BehandlingÅrsakV2,
)

data class UtbetalingsperiodeDVHV2(val hjemmel: String,
                                   val utbetaltPerMnd: Int,
                                   val stønadFom: LocalDate,
                                   val stønadTom: LocalDate,
                                   val utbetalingsDetaljer: List<UtbetalingsDetaljDVHV2>)

data class UtbetalingsDetaljDVHV2(val person: PersonDVHV2,
                                  val klassekode: String,
                                  val utbetaltPrMnd: Int,
                                  val delytelseId: String)


data class PersonDVHV2(val personIdent: String,
                       val rolle: String,
                       val statsborgerskap: List<String>,
                       val bostedsland: String,
                       val delingsprosentYtelse: Int)

data class Kompetanse(val barnsIdenter: List<String>,
                      val fom: YearMonth,
                      val tom: YearMonth?,
                      val sokersaktivitet: SøkersAktivitet,
                      val annenForeldersAktivitet: AnnenForeldersAktivitet? = null,
                      val annenForeldersAktivitetsland: String? = null,
                      val barnetsBostedsland: String? = null,
                      val resultat: KompetanseResultat? = null
)

enum class SøkersAktivitet {
    ARBEIDER_I_NORGE,
    SELVSTENDIG_NÆRINGSDRIVENDE,
    MOTTAR_UTBETALING_FRA_NAV_SOM_ERSTATTER_LØNN,
    UTSENDT_ARBEIDSTAKER_FRA_NORGE,
    MOTTAR_UFØRETRYGD_FRA_NORGE,
    MOTTAR_PENSJON_FRA_NORGE,
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
    TEKNISK_OPPHØR("Teknisk opphør"), // Ikke i bruk lenger
    TEKNISK_ENDRING("Teknisk endring")
}

enum class BehandlingÅrsakV2(val visningsnavn: String) {

    SØKNAD("Søknad"),
    FØDSELSHENDELSE("Fødselshendelse"),
    ÅRLIG_KONTROLL("Årsak kontroll"),
    DØDSFALL_BRUKER("Dødsfall bruker"),
    NYE_OPPLYSNINGER("Nye opplysninger"),
    KLAGE("Klage"),
    TEKNISK_OPPHØR("Teknisk opphør"), // Ikke i bruk lenger
    TEKNISK_ENDRING("Teknisk endring"),
    KORREKSJON_VEDTAKSBREV("Korrigere vedtak med egen brevmal"),
    OMREGNING_6ÅR("Omregning 6 år"),
    OMREGNING_18ÅR("Omregning 18 år"),
    OMREGNING_SMÅBARNSTILLEGG("Omregning småbarnstillegg"),
    SMÅBARNSTILLEGG("Småbarnstillegg"),
    MIGRERING("Migrering"),
    SATSENDRING("Satsendring"),
    ENDRE_MIGRERINGSDATO("Endre migreringsdato"),
    HELMANUELL_MIGRERING("Manuell migrering")
}

enum class KategoriV2 {
    EØS,
    NASJONAL
}

enum class UnderkategoriV2 {
    UTVIDET,
    ORDINÆR
}

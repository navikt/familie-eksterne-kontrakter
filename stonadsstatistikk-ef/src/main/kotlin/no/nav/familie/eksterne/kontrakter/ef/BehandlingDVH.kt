package no.nav.familie.eksterne.kontrakter.ef

import java.time.LocalDate
import java.time.ZonedDateTime

@Deprecated("Bruk BehandlingOvergangsstønadDVH")
typealias BehandlingDVH = VedtakOvergangsstønadDVH

data class VedtakOvergangsstønadDVH(
        val fagsakId: Long, // Ekstern fagsakId
        val behandlingId: Long, // Ekstern behandlingId
        val relatertBehandlingId: Long? = null, // Ekstern behandlingId på relatert behandling
        val adressebeskyttelse: Adressebeskyttelse? = null,
        val tidspunktVedtak: ZonedDateTime? = null,
        val vilkårsvurderinger: List<VilkårsvurderingDto>,
        val person: Person,
        val barn: List<Barn>,
        val behandlingType: BehandlingType,
        val behandlingÅrsak: BehandlingÅrsak,
        val vedtak: Vedtak? = null,
        val vedtaksperioder: List<VedtaksperiodeOvergangsstønadDto>,
        val utbetalinger: List<Utbetaling>,
        val aktivitetskrav: Aktivitetskrav,
        val funksjonellId: Long? = null,
        val stønadstype: StønadType
)

data class VedtakBarnetilsynDVH(
        val fagsakId: Long, // Ekstern fagsakId
        val behandlingId: Long, // Ekstern behandlingId
        val relatertBehandlingId: Long? = null, // Ekstern behandlingId på relatert behandling
        val adressebeskyttelse: Adressebeskyttelse? = null,
        val tidspunktVedtak: ZonedDateTime? = null,
        val vilkårsvurderinger: List<VilkårsvurderingDto>,
        val person: Person,
        val barn: List<Barn>,
        val behandlingType: BehandlingType,
        val behandlingÅrsak: BehandlingÅrsak,
        val vedtak: Vedtak? = null,
        val vedtaksperioder: List<VedtaksperiodeBarnetilsynDto>,
        val utbetalinger: List<Utbetaling>,
        val aktivitetskrav: Aktivitetskrav,
        val funksjonellId: Long? = null,
        val stønadstype: StønadType,
        val perioderKontantstøtte: List<PeriodeMedBeløp>,
        val perioderTilleggsstønad: List<PeriodeMedBeløp>
)

enum class BehandlingType {
    BLANKETT,
    FØRSTEGANGSBEHANDLING,
    REVURDERING,  // Inkluderer opphør
    KLAGE,
    TEKNISK_OPPHØR,
    MIGRERING_FRA_INFOTRYGD,
    TILBAKEFØRING_TIL_INFOTRYGD
}

enum class BehandlingÅrsak {
    SØKNAD,
    NYE_OPPLYSNINGER,
    SANKSJON_1_MND,
    KLAGE,
    MIGRERING
}

enum class Vedtak {
    INNVILGET,
    OPPHØRT,
    AVSLÅTT
}

enum class Adressebeskyttelse {
    STRENGT_FORTROLIG,
    STRENGT_FORTROLIG_UTLAND,
    FORTROLIG,
    UGRADERT
}

data class Person(val personIdent: String? = null)

data class Barn(val personIdent: String? = null, val termindato: LocalDate? = null)

data class Utbetaling(
        val beløp: Int,
        var samordningsfradrag: Int,
        val inntekt: Int,
        val inntektsreduksjon: Int,
        val fraOgMed: LocalDate,
        val tilOgMed: LocalDate,
        val utbetalingsdetalj: Utbetalingsdetalj,
)

data class Utbetalingsdetalj(val gjelderPerson: Person,
                             val klassekode: String, // Identifiserer detaljert stønadstype i oppdragsystemet: "EFOG", "EFBT" og "EFSP"
                             val delytelseId: String) // Identifiderer utbetalingen i oppdragssystemet

data class VilkårsvurderingDto(
        val vilkår: Vilkår,
        val resultat: Vilkårsresultat
)

enum class Vilkårsresultat {
    OPPFYLT,
    IKKE_OPPFYLT,
    IKKE_AKTUELL,
    IKKE_TATT_STILLING_TIL,
    SKAL_IKKE_VURDERES;
}

enum class Vilkår {
    FORUTGÅENDE_MEDLEMSKAP,
    LOVLIG_OPPHOLD,
    MOR_ELLER_FAR,
    SIVILSTAND,
    SAMLIV,
    ALENEOMSORG,
    NYTT_BARN_SAMME_PARTNER,
    SAGT_OPP_ELLER_REDUSERT,
    AKTIVITET,
    AKTIVITET_ARBEID,
    TIDLIGERE_VEDTAKSPERIODER,
    INNTEKT,
    ALDER_PÅ_BARN
}

data class Aktivitetskrav(val aktivitetspliktInntrefferDato: LocalDate?, val harSagtOppArbeidsforhold: Boolean?)

@Deprecated("Bruk VedtaksperiodeOvergangsstønadDto")
typealias VedtaksperiodeDto = VedtaksperiodeOvergangsstønadDto

data class VedtaksperiodeOvergangsstønadDto(
        val fraOgMed: LocalDate,
        val tilOgMed: LocalDate,
        val aktivitet: AktivitetType,
        val periodeType: VedtaksperiodeType
)

data class VedtaksperiodeBarnetilsynDto(
        val fraOgMed: LocalDate,
        val tilOgMed: LocalDate,
        val utgifter: Int,
        val antallBarn: Int
)


data class PeriodeMedBeløp(
        val fraOgMed: LocalDate,
        val tilOgMed: LocalDate,
        val beløp: Int
)

enum class VedtaksperiodeType {
    MIGRERING,
    FORLENGELSE,
    HOVEDPERIODE,
    SANKSJON,
    PERIODE_FØR_FØDSEL,
    UTVIDELSE,
    NY_PERIODE_FOR_NYTT_BARN
}

enum class AktivitetType {
    MIGRERING,
    IKKE_AKTIVITETSPLIKT,
    BARN_UNDER_ETT_ÅR,
    FORSØRGER_I_ARBEID,
    FORSØRGER_I_UTDANNING,
    FORSØRGER_REELL_ARBEIDSSØKER,
    FORSØRGER_ETABLERER_VIRKSOMHET,
    BARNET_SÆRLIG_TILSYNSKREVENDE,
    FORSØRGER_MANGLER_TILSYNSORDNING,
    FORSØRGER_ER_SYK,
    BARNET_ER_SYKT,
    UTVIDELSE_FORSØRGER_I_UTDANNING,
    UTVIDELSE_BARNET_SÆRLIG_TILSYNSKREVENDE,
    FORLENGELSE_MIDLERTIDIG_SYKDOM,
    FORLENGELSE_STØNAD_PÅVENTE_ARBEID,
    FORLENGELSE_STØNAD_PÅVENTE_ARBEID_REELL_ARBEIDSSØKER,
    FORLENGELSE_STØNAD_PÅVENTE_OPPSTART_KVALIFISERINGSPROGRAM,
    FORLENGELSE_STØNAD_PÅVENTE_TILSYNSORDNING,
    FORLENGELSE_STØNAD_PÅVENTE_UTDANNING,
    FORLENGELSE_STØNAD_UT_SKOLEÅRET,
}

enum class StønadType {
    OVERGANGSSTØNAD,
    BARNETILSYN,
    SKOLEPENGER
}


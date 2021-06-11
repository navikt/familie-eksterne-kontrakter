package no.nav.familie.eksterne.kontrakter.ef

import com.fasterxml.jackson.annotation.JsonUnwrapped
import java.time.LocalDate
import java.time.ZonedDateTime

data class BehandlingDVH(
        val fagsakId: String,
        val behandlingId: String,
        val relatertBehandlingId: String? = null,
        val adressebeskyttelse: Adressebeskyttelse? = null,
        val tidspunktVedtak: ZonedDateTime? = null,
        val vilkårsvurderinger: List<VilkårsvurderingDto>,
        val person: Person,
        val barn: List<Barn>,
        val behandlingType: BehandlingType,
        val behandlingÅrsak: BehandlingÅrsak,
        val vedtak: Vedtak? = null,
        val vedtaksperioder: List<VedtaksperiodeDto>,
        val utbetalinger: List<Utbetaling>,
        val aktivitetskrav: Aktivitetskrav,
        val funksjonellId: String? = null,
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
    PERIODISK_KONTROLL,
    ENDRET_SATS,
    DØDSFALL,
    NYE_OPPLYSNINGER,
    MIGRERING,
    TEKNISK_FEIL // Kan være feilutbetaling, funksjonelle mangler
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
        @JsonUnwrapped val utbetalingsdetalj: Utbetalingsdetalj,
)

data class Utbetalingsdetalj(@JsonUnwrapped val gjelderPerson: Person,
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
    TIDLIGERE_VEDTAKSPERIODER;
}

data class Aktivitetskrav(val aktivitetspliktInntrefferDato: LocalDate?, val harSagtOppArbeidsforhold: Boolean?)

data class VedtaksperiodeDto(
        val fraOgMed: LocalDate,
        val tilOgMed: LocalDate,
        val aktivitet: String,
        val periodeType: String
)


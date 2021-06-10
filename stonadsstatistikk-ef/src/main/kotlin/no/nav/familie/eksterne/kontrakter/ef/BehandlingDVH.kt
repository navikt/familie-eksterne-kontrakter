package no.nav.familie.eksterne.kontrakter.ef

import com.fasterxml.jackson.annotation.JsonUnwrapped
import java.time.LocalDate
import java.time.ZonedDateTime

data class BehandlingDVH(
        val fagsakId: String,
        val behandlingId: String,
        val relatertBehandlingId: String? = null,
        val adressebeskyttelse: Adressebeskyttelse,
        val tidspunktVedtak: ZonedDateTime? = null,
        val vilkårsvurderinger: List<Vilkårsvurdering>,
        val person: Person,
        val barn: List<Barn>,
        val behandlingType: BehandlingType,
        val behandlingÅrsak: BehandlingÅrsak,
        val vedtak: Vedtak? = null,
        val vedtaksperiode: Vedtaksperiode,
        val utbetalinger: List<Utbetaling>,
        val aktivitetskrav: Aktivitetskrav,
        val funksjonellId: String? = null,
)

enum class BehandlingType {
    SAKSBEHANDLINGSBLANKETT,
    FØRSTEGANGSBEHANDLING,
    REVURDERING,  // Inkluderer opphør
    KLAGE,
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

data class Vilkårsvurdering(
        val vilkår: Vilkår,
        val resultat: Resultat
)

enum class Resultat {
    OPPFYLT,
    IKKE_OPPFYLT,
    IKKE_VURDERT
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

data class Aktivitetskrav(val harSagtOppArbeidsforhold: Boolean?)

data class Vedtaksperiode(
        val fraOgMed: LocalDate,
        val tilOgMed: LocalDate,
        val aktivitet: String,
        val periodeType: String
)


package no.nav.familie.eksterne.kontrakter.ef

import com.fasterxml.jackson.annotation.JsonUnwrapped
import java.time.LocalDate
import java.time.ZonedDateTime

data class BehandlingDVH(
        val fagsakId: String,
        val saksnummer: String? = null,
        val behandlingId: String,
        val relatertBehandlingId: String? = null,
        val kode6eller7: Boolean,
        val tidspunktVedtak: ZonedDateTime? = null,
        val vilkårsvurderinger: List<Vilkårsvurdering>,
        val person: Person,
        val barn: List<Person>,
        val behandlingType: BehandlingType,
        val behandlingÅrsak: BehandlingÅrsak,
        val vedtak: Vedtak? = null,
        val utbetalinger: List<Utbetaling>,
        val inntekt: List<Inntekt>,
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

enum class BehandlingResultat {
    FERDIGSTILT,
    DUPLIKAT,
    HENLAGT,
    ANNULLERT
}

enum class Vedtak {
    INNVILGET,
    DELVIS_INNVILGET,
    OPPHØRT,
    AVSLÅTT
}

data class Person(val personIdent: String? = null)

data class Utbetaling(val beløp: Int,
                      val fraOgMed: LocalDate,
                      val tilOgMed: LocalDate,
                      @JsonUnwrapped val utbetalingsdetalj: Utbetalingsdetalj)

data class Utbetalingsdetalj(val klassekode: String, // Identifiserer detaljert stønadstype i oppdragsystemet: "EFOG", "EFBT" og "EFSP"
                             val delytelseId: String) // Identifiderer utbetalingen i oppdragssystemet

data class Inntekt(
        val beløp: Int,
        var samordningsfradrag: Int,
        val fraOgMed: LocalDate,
        val tilOgMed: LocalDate,
)

enum class Inntektstype {
    ARBEIDINNTEKT,
    KAPITALINNTEKT,
    TRYGD_ELLER_STØNAD
}

data class Vilkårsvurdering(
        val vilkår: Vilkår,
        val oppfylt: Boolean
)

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

data class Aktivitetskrav(val harSagtOppArbeidsforhold: Boolean)


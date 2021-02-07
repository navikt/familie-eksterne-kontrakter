package no.nav.familie.eksterne.kontrakter.ef

import java.time.LocalDate
import java.time.ZonedDateTime

data class Vedtak(
        val sakId: String,
        val behandlingId: String,
        val relatertBehandlingId: String,
        val kode6eller7: Boolean,
        val tidspunktVedtak: ZonedDateTime,
        val vilkårsvurderinger: List<Vilkårsvurdering>,
        val person: Person,
        val barn: List<Person>,
        val behandlingType: BehandlingType,
        val behandlingÅrsak: BehandlingÅrsak,
        val behandlingResultat: BehandlingResultat,
        val vedtakResultat: VedtakResultat? = null,
        val utbetalinger: List<Utbetaling>,
        val inntekt: List<Inntekt>,
        val aktivitetskrav: Aktivitetskrav,
        val funksjonellId: String? = null,
)

enum class BehandlingType {
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
    FULLFØRT,
    DUPLIKAT,
    HENLAGT
}

enum class VedtakResultat {
    INNVILGET,
    DELVIS_INNVILGET,
    OPPHØRT,
    AVSLÅTT
}

data class Person(
        val personIdent: String? = null,
        val aktorId: String? = null
)

data class Utbetaling(val periodeBeløp: PeriodeBeløp,
                      val utbetalingsdetalj: Utbetalingsdetalj
)

data class PeriodeBeløp(
        val utbetaltPerPeriode: Int,
        var periodetype: Periodetype,
        val fraOgMed: LocalDate,
        val tilOgMed: LocalDate,
)

data class Utbetalingsdetalj(val gjelderPerson: Person, // Identifiserer hvilken person utbetalingen gjelder, ikke nødvendigvis brukeren selv
                             val klassekode: String, // Identifiserer detaljert stønadstype i oppdragsystemet
                             val delytelseId: String) // Identifiderer utbetalingen i oppdragssystemet

enum class Periodetype {
    MÅNED
}

data class Inntekt(
        val periodeBeløp: PeriodeBeløp,
        val inntektstype: Inntektstype
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
    OPPHOLDSTILLATELSE,
    FORUTGÅENDE_MEDLEMSKAP,
    LOVLIG_OPPHOLD,
    SIVILSTAND
}

data class Aktivitetskrav(
        val aktivitetspliktInntrefferDato: LocalDate,
        val harSagtOppArbeidsforhold: Boolean
)


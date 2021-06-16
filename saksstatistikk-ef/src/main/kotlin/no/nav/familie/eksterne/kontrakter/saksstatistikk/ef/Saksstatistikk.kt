package no.nav.familie.eksterne.kontrakter.saksstatistikk.ef

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate
import java.time.ZonedDateTime

data class BehandlingDVH(
        val behandlingId: String, // Fagsystemets behandlings-ID
        val personIdent: String, // PersonIdent tilknyttet søker eller hovedaktør for ytelsen
        val registrertTid: ZonedDateTime, // Tidspunkt da behandlingen først oppstod eller ble registrert i fagsystemet
        val endretTid: ZonedDateTime, // Tidspunkt for siste endring på behandlingen. Ved første melding vil denne være lik registrertTid
        val tekniskTid: ZonedDateTime, // Tidspunktet da fagsystemet legger hendelsen på grensesnittet/topicen
        val sakYtelse: String, // Kode som angir hvilken ytelse/stønad behandlingen gjelder
        val behandlingType: String, // Kode som angir hvilken type behandling det er snakk om - typisk: søknad, revurdering, tilbakekreving, klage, etc.
        val behandlingStatus: String, // Kode som angir hvilken status behandlingen har - typisk: opprettet, under behandling, avsluttet, etc
        val opprettetAv: String, // [Feltet er geo-lokaliserende og skal oppgis som -5 hvis noen personer tilknyttet behandlingen er kode 6] Saksbehandler-ID som opprettet behandlingen. Hvis det er en servicebruker så sende denne
        val opprettetEnhet: String, // [Feltet er geo-lokaliserende og skal oppgis som -5 hvis noen personer tilknyttet behandlingen er kode 6] Hvilken org enhet som behandlingen opprinnelig ble rutet til i NAV. Dette kan også være en nasjonal kø
        val ansvarligEnhet: String, // [Feltet er geo-lokaliserende og skal oppgis som -5 hvis noen personer tilknyttet behandlingen er kode 6] Hvilken org enhet som nå har ansvar for saken. Dette kan være samme som opprettetEnhet. Avslåtte klager i vedtaksinstans skal ha riktig KA-enhet her
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val behandlingUuid: String? = null, // Behandlingens UUID
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val relatertBehandlingId: String? = null, // Hvis behandlingen har oppsått med bakgrunn i en annen, skal den foregående behandlingen refereres til her
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val sakId: String? = null, // Hvis fagsystemet har et forhold til sak, skal saks-IDen oppgis her
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val saksnummer: String? = null, // Saksnummer som følger behandlingen for NAV globalt
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val mottattTid: ZonedDateTime? = null, // Tidspunktet da behandlingen oppstår (eks søknadstidspunkt, inntektsmelding, etc). Det er ønskelig å måle brukers opplevde ventetid. Ved elektronisk kontakt regner vi med at denne er lik registrertTid
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val ferdigBehandletTid: ZonedDateTime? = null, // Tidspunkt når behandling ble avsluttet, enten avbrutt, henlagt, vedtak innvilget/avslått, etc
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val vedtakTid: ZonedDateTime? = null, // Tidspunktet for når vedtaket ble fattet - hvis saken ble vedtatt
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val datoForsteUtbetaling: LocalDate? = null, // Hvis systemet eller bruker har et forhold til når ytelsen normalt skal utbetales (planlagt uttak, ønsket oppstart etc)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val sakUtland: String? = null, // Kode som angir hvor vidt saken er for utland eller nasjonal å anses. Se begrepskatalogen: https://jira.adeo.no/browse/BEGREP-1611#
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val venteAarsak: String? = null, // Kode som angir årsak til venting/utsettelse av saksbehandlings prosesser - typisk: venter på utland, venter på inntektsmelding etc.
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val behandlingResultat: String? = null, // Kode som angir resultatet på behandling - typisk: avbrutt, innvilget, delvis innvilget, henlagt av tekniske hensyn, etc
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val resultatBegrunnelse: String? = null, // Kode som angir en begrunnelse til resultat - typisk: vilkårsprøvingen feilet, dublett, teknisk avvik, etc
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val behandlingMetode: String? = null, // Kode som angir om saken er behandlet manuelt eller automatisk (hvis fagsystemet opererer med en slik verdi)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val saksbehandler: String? = null, // [Feltet er geo-lokaliserende og skal oppgis som -5 hvis noen personer tilknyttet behandlingen er kode 6] Saksbehandler-ID som sist var involvert i behandlingen
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val ansvarligBeslutter: String? = null, // [Feltet er geo-lokaliserende og skal oppgis som -5 hvis noen personer tilknyttet behandlingen er kode 6, men kun om det skulle hatt verdi] Ved krav om totrinnskontroll skal dette feltet innholde ansvarlig beslutter sin ID
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val avsender: String? = null, // Angir fagsystemets eget navn
        @JsonInclude(JsonInclude.Include.NON_NULL)val versjon: String? = null, // "Kode som hvilken versjonen av koden dataene er generert med bakgrunn på. Kan godt være relatert til Git repoet
)

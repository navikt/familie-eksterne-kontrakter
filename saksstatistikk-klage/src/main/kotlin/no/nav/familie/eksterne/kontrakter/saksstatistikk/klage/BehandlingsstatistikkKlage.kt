package no.nav.familie.eksterne.kontrakter.saksstatistikk.klage

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.ZonedDateTime
import java.util.UUID

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BehandlingsstatistikkKlage(
    val behandlingId: UUID, // Fagsystemets eksterne behandlings-ID
    val personIdent: String, // PersonIdent tilknyttet søker eller hovedaktør for ytelsen
    val registrertTid: ZonedDateTime, // Tidspunkt da behandlingen først oppstod eller ble registrert i fagsystemet
    val endretTid: ZonedDateTime, // Tidspunkt for siste endring på behandlingen. Ved første melding vil denne være lik registrertTid
    val tekniskTid: ZonedDateTime, // Tidspunktet da fagsystemet legger hendelsen på grensesnittet/topicen
    val sakYtelse: String, // Kode som angir hvilken ytelse/stønad behandlingen gjelder
    val relatertEksternBehandlingId: String? = null, // Fagsystemet sin eksterne behandlingId, hvis klagen er koblet til en behandling
    // klage / anke?
    // val behandlingType: String, // Kode som angir hvilken type behandling det er snakk om - typisk: søknad, revurdering, tilbakekreving, klage, etc.
    val behandlingStatus: String, // Kode som angir hvilken status behandlingen har - typisk: opprettet, under behandling, avsluttet, etc
    val opprettetAv: String, // [Feltet er geo-lokaliserende og skal oppgis som -5 hvis noen personer tilknyttet behandlingen er kode 6] Saksbehandler-ID som opprettet behandlingen. Hvis det er en servicebruker så sende denne
    // Trenger vi begge disse enhetene? Har vi denne informasjonen?
    val opprettetEnhet: String, // [Feltet er geo-lokaliserende og skal oppgis som -5 hvis noen personer tilknyttet behandlingen er kode 6] Hvilken org enhet som behandlingen opprinnelig ble rutet til i NAV. Dette kan også være en nasjonal kø
    val ansvarligEnhet: String, // [Feltet er geo-lokaliserende og skal oppgis som -5 hvis noen personer tilknyttet behandlingen er kode 6] Hvilken org enhet som nå har ansvar for saken. Dette kan være samme som opprettetEnhet. Avslåtte klager i vedtaksinstans skal ha riktig KA-enhet her
    val sakId: String, // Saksnummer som følger behandlingen for NAV globalt
    val mottattTid: ZonedDateTime? = null, // Tidspunktet da behandlingen oppstår (eks søknadstidspunkt, inntektsmelding, etc). Det er ønskelig å måle brukers opplevde ventetid. Ved elektronisk kontakt regner vi med at denne er lik registrertTid
    val ferdigBehandletTid: ZonedDateTime? = null, // Tidspunkt når behandling ble avsluttet, enten avbrutt, henlagt, vedtak innvilget/avslått, etc
    val vedtakTid: ZonedDateTime? = null, // Tidspunktet for når vedtaket ble fattet - hvis saken ble vedtatt
    val sakUtland: String? = null, // Nasjonal/Utland - Kode som angir hvor vidt saken er for utland eller nasjonal å anses. Se begrepskatalogen: https://jira.adeo.no/browse/BEGREP-1611#
    val behandlingResultat: String? = null, // Kode som angir resultatet på behandling - typisk: avbrutt, innvilget, delvis innvilget, henlagt av tekniske hensyn, etc
    val resultatBegrunnelse: String? = null, // Kode som angir en begrunnelse til resultat - typisk: vilkårsprøvingen feilet, dublett, teknisk avvik, etc
    val behandlingMetode: String? = null, // Kode som angir om saken er behandlet manuelt eller automatisk (hvis fagsystemet opererer med en slik verdi)
    val saksbehandler: String? = null, // [Feltet er geo-lokaliserende og skal oppgis som -5 hvis noen personer tilknyttet behandlingen er kode 6] Saksbehandler-ID som sist var involvert i behandlingen
    val avsender: String? = null, // Angir fagsystemets eget navn
)

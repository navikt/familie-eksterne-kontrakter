package no.nav.familie.eksterne.kontrakter.saksstatistikk.klage

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime
import java.util.UUID

internal class BehandlingsstatistikkKlageTest {
    @Test
    fun `serialiser og deserialiser, forvent ingen unntak`() {
        val mapper = ObjectMapper().registerModule(KotlinModule())
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        val json = mapper.writeValueAsString(opprettBehandlingstatistikk())
        mapper.readValue<BehandlingsstatistikkKlage>(json)
    }

    private fun opprettBehandlingstatistikk(): BehandlingsstatistikkKlage =
        BehandlingsstatistikkKlage(
            behandlingId = UUID.randomUUID(),
            behandlingType = "Klage",
            fagsystem = "EF",
            personIdent = "persinIdent",
            saksbehandler = "gjeldendeSaksbehandlerId",
            registrertTid = ZonedDateTime.now(),
            endretTid = ZonedDateTime.now(),
            tekniskTid = ZonedDateTime.now(),
            sakYtelse = "EFOG",
            behandlingStatus = "MOTTATT",
            opprettetAv = "gjeldendeSaksbehandlerId",
            opprettetEnhet = "",
            ansvarligEnhet = "",
            saksnummer = "123",
            mottattTid = ZonedDateTime.now(),
            behandlingMetode = "MANUELL",
            avsender = "NAV Enslig forelder",
            relatertEksternBehandlingId = "1",
        )
}

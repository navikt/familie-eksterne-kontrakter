package no.nav.familie.eksterne.kontrakter.saksstatistikk.ef

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZonedDateTime

internal class BehandlingDVHTest {
    @Test
    fun `serialiser og deserialiser, forvent ingen unntak`() {
        val mapper = ObjectMapper().registerModule(kotlinModule())
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        val json = mapper.writeValueAsString(opprettBehandlingstatistikk())
        mapper.readValue<BehandlingDVH>(json)
    }

    private fun opprettBehandlingstatistikk(): BehandlingDVH =
        BehandlingDVH(
            behandlingId = 123L,
            personIdent = "persinIdent",
            saksbehandler = "gjeldendeSaksbehandlerId",
            registrertTid = ZonedDateTime.now(),
            endretTid = ZonedDateTime.now(),
            tekniskTid = ZonedDateTime.now(),
            sakYtelse = "EFOG",
            behandlingType = "Førstegangsbehandling",
            behandlingStatus = "MOTTATT",
            opprettetAv = "gjeldendeSaksbehandlerId",
            opprettetEnhet = "",
            ansvarligEnhet = "",
            saksnummer = 123L,
            mottattTid = ZonedDateTime.now(),
            behandlingMetode = "MANUELL",
            avsender = "NAV Enslig forelder",
            totrinnsbehandling = true,
            sakId = 321L,
            kravMottatt = LocalDate.now(),
            revurderingÅrsak = "ENDRING_I_INNTEKT",
            revurderingOpplysningskilde = "MODIA",
        )
}

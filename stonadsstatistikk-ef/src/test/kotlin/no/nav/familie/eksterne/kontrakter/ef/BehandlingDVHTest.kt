package no.nav.familie.eksterne.kontrakter.ef

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*


class BehandlingDVHTest {

    val vedtak = BehandlingDVH(
            fagsakId = 100L,
            behandlingId = 123L,
            relatertBehandlingId = 110L,
            adressebeskyttelse = Adressebeskyttelse.UGRADERT,
            tidspunktVedtak = ZonedDateTime.now(),
            vilkårsvurderinger = listOf(
                    VilkårsvurderingDto(Vilkår.LOVLIG_OPPHOLD, Vilkårsresultat.OPPFYLT),
                    VilkårsvurderingDto(Vilkår.SIVILSTAND, Vilkårsresultat.OPPFYLT)
            ),
            person = Person(personIdent = "5634422"),
            barn = listOf(Barn(personIdent = "6442433")),
            behandlingType = BehandlingType.REVURDERING,
            behandlingÅrsak = BehandlingÅrsak.SØKNAD,
            vedtak = Vedtak.INNVILGET,
            vedtaksperioder = listOf(VedtaksperiodeDto(LocalDate.of(2021, 3, 1),
                                                       LocalDate.of(2024, 2, 29),
                                                       AktivitetType.BARNET_ER_SYKT, VedtaksperiodeType.HOVEDPERIODE)),
            utbetalinger = listOf(
                    Utbetaling(
                            beløp = 100,
                            samordningsfradrag = 0,
                            inntekt = 100,
                            inntektsreduksjon = 0,
                            LocalDate.of(2021, 3, 1),
                            LocalDate.of(2024, 2, 29),
                            Utbetalingsdetalj(Person(personIdent = "5634422"), "EFOG", "EF-123-EF-019")
                    )
            ),
            aktivitetskrav = Aktivitetskrav(
                    aktivitetspliktInntrefferDato = LocalDate.of(2021, 3, 1),
                    harSagtOppArbeidsforhold = false
            ),
            stønadstype = StønadType.OVERGANGSSTØNAD,

    )

    @Test
    fun `serialiser og deserialiser, forvent ingen unntak`() {

        val mapper = ObjectMapper().registerModule(KotlinModule())
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        val json = mapper.writeValueAsString(vedtak)
        mapper.readValue<BehandlingDVH>(json)

    }

}
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Artipie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.artipie.pypi.meta;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test for {@link Metadata.FromArchive}.
 * @since 0.6
 */
class MetadataFromArchiveTest {

    @ParameterizedTest
    @CsvSource({
        "pypi_repo/artipie-sample-0.2.zip",
        "pypi_repo/artipie-sample-0.2.tar",
        "pypi_repo/artipie-sample-0.2.tar.gz",
        "pypi_repo/artipie-sample-2.1.tar.Z",
        "pypi_repo/artipie-sample-2.1.tar.bz2",
        "pypi_repo/artipie_sample-0.2-py3-none-any.whl"
    })
    void readsFromTarGz(final String filename) {
        MatcherAssert.assertThat(
            new Metadata.FromArchive(this.resource(filename)).read().name(),
            new IsEqual<>("artipie-sample")
        );
    }

    @Test
    void throwsExceptionIfArchiveIsUnsupported() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new Metadata.FromArchive(Paths.get("some/archive.tar.br")).read()
        );
    }

    private Path resource(final String name) {
        try {
            return Paths.get(
                Thread.currentThread().getContextClassLoader().getResource(name).toURI()
            );
        } catch (final URISyntaxException ex) {
            throw new IllegalStateException("Failed to load test recourses", ex);
        }
    }

}

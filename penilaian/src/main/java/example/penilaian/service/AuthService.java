package example.penilaian.service;

import example.penilaian.entity.Users;
import example.penilaian.model.LoginUserRequest;
import example.penilaian.model.ValidatorService;
import example.penilaian.model.WebResponse;
import example.penilaian.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidatorService validatorService;

    @Transactional
    public WebResponse<String> login(LoginUserRequest request) {
        validatorService.validate(request);

        Users user = userRepository.findByNip(request.getNip())
                .orElseThrow(() -> new ResponseStatusException(UNAUTHORIZED, "NIP or Password Salah"));

        // Periksa apakah kata sandi yang dimasukkan oleh pengguna sama dengan yang ada di database.
        if (user.getPassword().equals(request.getPassword())) {
            // Password cocok, Anda dapat mengizinkan akses ke pengguna.

            // Contoh WebResponse jika login berhasil:
            return WebResponse.<String>builder().data("Login Success").build();
        } else {
            throw new ResponseStatusException(UNAUTHORIZED, "NIP or Password Salah");
        }
    }
}

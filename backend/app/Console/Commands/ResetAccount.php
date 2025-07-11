<?php

namespace App\Console\Commands;

use App\Models\User;
use Illuminate\Console\Command;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\Rules;

class ResetAccount extends Command
{
    /**
     * The name and signature of the console command.
     *
     * @var string
     */
    protected $signature = 'account:reset';

    /**
     * The console command description.
     *
     * @var string
     */
    protected $description = 'Resets an account password';

    /**
     * Execute the console command.
     */
    public function handle()
    {
        $email = $this->ask('Correo electrónico: ');
        $password = $this->secret('Contraseña: ');
        $passwordConfirmation = $this->secret('Confirma tu contraseña: ');

        $accountData = [
            'email' => $email,
            'password' => $password,
            'password_confirmation' => $passwordConfirmation
        ];

        $validator = Validator::make($accountData, [
            'email' => 'required|string|lowercase|email|max:255',
            'password' => ['required', 'confirmed', Rules\Password::defaults()],
        ]);

        if ($validator->fails()) {
            $this->error($validator->errors()->first());
            return;
        }

        $user = User::whereEmail($email)->first();
        if (!$user) {
            $this->error('User with email ' . $email . ' not found.');
            return;
        }

        $user->update([
            'password' => Hash::make($password)
        ]);
        $this->info('Account password reset successfully');
    }
}
